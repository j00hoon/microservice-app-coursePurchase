import { Link } from "react-router-dom";

// function NotFoundPage();
const NotFoundPage = () => {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12 text-center">
                    <span className="display-1">
                        404
                    </span>
                    <div className="mb-4 lead">
                        Oops! 404 Not found!
                    </div>

                    <Link to="/home" className="btn btn-link">
                        Back to home
                    </Link>
                </div>
            </div>
        </div>
    );
};

export {NotFoundPage};